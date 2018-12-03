package org.woo.mservice.metadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaDataService implements IMetaDataService {
    protected static final Logger log = LoggerFactory.getLogger(MetaDataService.class);

    @Override
    public void showType() {
    }

    public MetaDataService() {
        log.info(MetaDataService.class.getSimpleName());
    }
}
