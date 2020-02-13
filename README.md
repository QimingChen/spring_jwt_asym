# spring_jwt_asym

step 1: use key_generator to get a keypair, save to binary

step 2: auth get token: localhost:8080/gettoken, which is signed by privatekey

step 3: resource parse token: localhost:8080/parsetoken={token}, which is parsed by publickey


credit to:

1. https://stackoverflow.com/a/48924615

2. https://www.novixys.com/blog/how-to-generate-rsa-keys-java/
