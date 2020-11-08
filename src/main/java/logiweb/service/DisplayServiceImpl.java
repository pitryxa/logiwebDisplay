package logiweb.service;

import logiweb.dto.DisplayDto;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Singleton
@ApplicationScoped
public class DisplayServiceImpl implements DisplayService {
    @Inject
    private WebService webService;

    @Override
    public DisplayDto getDataForDisplay() {
        return webService.getDataForDisplay();
    }
}
