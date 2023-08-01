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
helm install app ./k8s/
```

```
kubectl port-forward service/nginx-ingress-nginx-controller 8080:80 -n m
```

```
curl -X POST -i -H "Content-Type:application/json" -H "Host:arch.homework" -d '{"name": "FrodoBaggins"}' http://localhost:8080/user
curl -X GET -i -H "Content-Type:application/json" -H "Host:arch.homework" http://localhost:8080/user/1
curl -X PUT -i -H "Content-Type:application/json" -H "Host:arch.homework" -d '{"name": "BilboBaggins"}' http://localhost:8080/user/1
curl -X DELETE -i -H "Content-Type:application/json" -H "Host:arch.homework" http://localhost:8080/user/1
```