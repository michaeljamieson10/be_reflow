package com.neighbor.service;

import com.neighbor.model.Agent;
import com.neighbor.model.Client;

public interface ClientService {
    Client get(Client client);
    Client createNewClient(Client client);

}
