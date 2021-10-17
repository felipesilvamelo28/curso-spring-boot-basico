package br.com.example.springbootdemo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JsonValidatorService {

    static void validateBookDTO(String dtoJson) throws IOException, ParseBookDTOException {
        //criando o mapper
        ObjectMapper objectMapper = new ObjectMapper();
        //criando o factory
        JsonSchemaFactory schemaFactory= JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        //carregar o arquivo do schema
        InputStream file = new ClassPathResource("bookdto.json").getInputStream();
        //criando os objetos obrigatorios conforme doc
        JsonNode json = objectMapper.readTree(dtoJson);
        JsonSchema schema = schemaFactory.getSchema(file);
        //validando
        Set<ValidationMessage> errors = schema.validate(json);

        if (!errors.isEmpty()){
            List<String> values = new ArrayList<>();
            errors.forEach(validationMessage -> values.add(validationMessage.getMessage()));
            throw new ParseBookDTOException(values.toString());
        }
    }
}
