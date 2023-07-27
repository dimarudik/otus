```
kubectl create namespace m && \
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ && \
helm repo update && \
helm install nginx ingress-nginx/ingress-nginx --namespace m 
```

```
sudo echo "127.0.0.1 arch.homework" >> /etc/hosts
```

```
kubectl port-forward service/nginx-ingress-nginx-controller 8000:80 -n m
```

```
curl http://arch.homework:8000/health
curl http://arch.homework:8000/otusapp/dima/health
```
