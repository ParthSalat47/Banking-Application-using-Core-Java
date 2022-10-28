Everything is working fine in this but...

1. The credentialsFile is storing only the latest user who signed up. Still, older users are magically authorised. Validating users is authentic.
2. The file path used to store user objects is absolute...need to make it relative file path.
3. Need to refactor code - keep various function files in differnt directories
