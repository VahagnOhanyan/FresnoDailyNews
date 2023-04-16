package org.fresno.repo;

import org.asynchttpclient.Response;

public interface NewsService {

    Response get() throws Exception;
}