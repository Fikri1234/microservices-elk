# microservices-elk
Integrate springboot microservices with ELK


== acesss
openssl genrsa -out access-keypair.pem 2048
openssl rsa -in keypair.pem -pubout -out access-public-key.pem
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out access-private-key.pem
== refresh
openssl genrsa -out refresh-keypair.pem 2048
openssl rsa -in keypair.pem -pubout -out refresh-public-key.pem
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out refresh-private-key.pem
