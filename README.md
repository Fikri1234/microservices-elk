# microservices-elk
Integrate springboot microservices with ELK


You are about to generate a public & private key pair. While this can be done programmatically, it might be clearer to perform this process manually here. 
I recommend creating a dedicated folder under /src/main/resources/certs for the key files.

**Acesss**

openssl genpkey -algorithm RSA -out acesss-private-key.pem -pkeyopt rsa_keygen_bits:4096 -aes256
openssl rsa -pubout -in acesss-private-key.pem -out acesss-public-key.pem

**Refresh**

openssl genpkey -algorithm RSA -out refresh-private-key.pem -pkeyopt rsa_keygen_bits:4096 -aes256
openssl rsa -pubout -in refresh-private-key.pem -out refresh-public-key.pem
