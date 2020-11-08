package logiweb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import logiweb.dto.DisplayDto;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;

@Singleton
@ApplicationScoped
public class WebServiceImpl implements WebService {
    private final String GET_DATA_URL = "http://localhost:8080/logiweb/rest/update-display";
    private final int STATUS_OK = 200;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public DisplayDto getDataForDisplay() {
        WebResource webResource = getWebResource();
        ClientResponse clientResponse = queryGet(webResource);
        DisplayDto displayDto = new DisplayDto();

        if (clientResponse.getStatus() == STATUS_OK) {
            try {
                displayDto =
                        objectMapper.readValue(clientResponse.getEntity(String.class), new TypeReference<DisplayDto>() {
                        });
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return displayDto;
    }

    private WebResource getWebResource() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);

        return client.resource(GET_DATA_URL);
    }

    protected ClientResponse queryGet(WebResource webResource) {
        return webResource.type(MediaType.APPLICATION_JSON)
                          .accept(MediaType.APPLICATION_JSON)
                          .get(ClientResponse.class);
    }
}
