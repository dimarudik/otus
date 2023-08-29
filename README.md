```
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ && \
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts \
helm repo add hashicorp https://helm.releases.hashicorp.com
helm repo update
```

```
git clone -b 15-BFF https://github.com/dimarudik/otus.git
cd otus
```

```
kubectl create namespace v && \
helm upgrade --install vault ./k8s/vault -n v --wait && \
kubectl -n v exec vault-0 -- vault operator init \
    -key-shares=1 \
    -key-threshold=1 \
    -format=json > cluster-keys-tmp.json && \
VAULT_UNSEAL_KEY=$(jq -r ".unseal_keys_b64[]" cluster-keys-tmp.json) && \
kubectl -n v exec vault-0 -- vault operator unseal $VAULT_UNSEAL_KEY
```

```
kubectl create namespace m && \
helm install prom prometheus-community/kube-prometheus-stack -f prometheus.yaml --atomic -n m && \
helm install pgexport prometheus-community/prometheus-postgres-exporter -n m -f postgres_exporter.yml && \
helm install nginx ingress-nginx/ingress-nginx -n m -f nginx-ingress.yaml --atomic \
    --set controller.metrics.enabled=true \
    --set-string controller.podAnnotations."prometheus\.io/scrape"="true" \
    --set-string controller.podAnnotations."prometheus\.io/port"="10254"
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

