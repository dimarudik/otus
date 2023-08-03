```
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ && \
helm repo update
```

```
kubectl create namespace m && \
helm install nginx ingress-nginx/ingress-nginx --namespace m 
```

```
git clone -b 7-CRUD https://github.com/dimarudik/otus.git
cd otus
helm upgrade --install app ./k8s/ --wait --atomic
```

```
kubectl port-forward service/nginx-ingress-nginx-controller 8080:80 -n m
```

```
newman run postman/7-CRUD.postman_collection.json
```