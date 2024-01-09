```
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ && \
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts \
helm repo update
```

```
git clone -b 15-BFF https://github.com/dimarudik/otus.git
cd otus
```

```
kubectl create namespace v && \
kubectl create namespace m
```

```
helm install vault ./k8s/vault --set "server.dev.enabled=true" -n v --wait && \
helm install prom prometheus-community/kube-prometheus-stack -f prometheus.yaml \ 
    --atomic -n m && \
helm install pgexport prometheus-community/prometheus-postgres-exporter -n m \ 
    -f postgres_exporter.yml && \
helm install nginx ingress-nginx/ingress-nginx -n m -f nginx-ingress.yaml --atomic \
    --set controller.metrics.enabled=true \
    --set-string controller.podAnnotations."prometheus\.io/scrape"="true" \
    --set-string controller.podAnnotations."prometheus\.io/port"="10254"
```

```
kubectl -n v exec -it vault-0 -- /bin/sh
vault secrets enable -path=internal kv-v2
vault kv put internal/database/config username="postgres" password="postgres"
vault auth enable kubernetes
vault write auth/kubernetes/config \
      kubernetes_host="https://$KUBERNETES_PORT_443_TCP_ADDR:443"
vault policy write internal-app - <<EOF
path "internal/data/database/config" {
   capabilities = ["read"]
}
EOF
```

```
kubectl create sa internal-app -n v
kubectl apply --filename ./tmp/deployment-orgchart.yaml
```

```
kubectl -n v exec vault-0 -- vault operator init \
    -key-shares=1 \
    -key-threshold=1 \
    -format=json > cluster-keys-tmp.json && \
VAULT_UNSEAL_KEY=$(jq -r ".unseal_keys_b64[]" cluster-keys-tmp.json) && \
kubectl -n v exec vault-0 -- vault operator unseal $VAULT_UNSEAL_KEY
```

```
helm upgrade --install app ./k8s/ --wait --atomic -n m
```

```
newman run postman/15-BFF.postman_collection.json --iteration-count 10
```

```
kubectl port-forward service/vault 8200 -n v
kubectl port-forward service/prometheus-operated 9090 -n m
kubectl port-forward service/prom-grafana 9000:80 -n m
kubectl port-forward service/nginx-ingress-nginx-controller 8080:80 -n m
```

```
http://localhost:9090
http://localhost:9000
```

```
helm show values prometheus-community/prometheus-postgres-exporter -n m
```

