```
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ && \
helm repo update
```

```
kubectl create namespace m && \
helm install nginx ingress-nginx/ingress-nginx --namespace m -f nginx-ingress.yaml 
```

```
git clone -b 6-Ingress https://github.com/dimarudik/otus.git
cd otus
k apply -f ./k8s/
```

```
kubectl port-forward service/nginx-ingress-nginx-controller 8000:80 -n m
```

```
curl http://arch.homework:8000/health
curl http://arch.homework:8000/otusapp/dima/health
```
