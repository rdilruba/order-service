package com.customer.order.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class SecretManager {

    private final AWSSecretsManager secretsManager;

    public SecretManager() {
        this.secretsManager = AWSSecretsManagerClientBuilder.standard()
                .withRegion(Regions.EU_CENTRAL_1)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }

    public DbSecret getSecret(String secretName) {

        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);

        GetSecretValueResult getSecretValueResponse = secretsManager.getSecretValue(getSecretValueRequest);
        String secret =  getSecretValueResponse.getSecretString();
        return parseSecret(secret);
    }

    private DbSecret parseSecret(String secret) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(secret, DbSecret.class);
        } catch (Exception e) {
            throw new RuntimeException("Unable to parse secret from AWS Secrets Manager", e);
        }
    }
    public static class DbSecret {
        public String username;
        public String password;
    }
}
