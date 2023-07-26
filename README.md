```
kubectl port-forward service/demo-service 8000:8000
```

```
kubectl create namespace m && \
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ && \
helm repo update && \
helm install nginx ingress-nginx/ingress-nginx --namespace m 
```
