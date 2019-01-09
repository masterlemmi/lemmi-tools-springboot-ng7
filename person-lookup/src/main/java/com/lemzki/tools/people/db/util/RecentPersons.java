package com.lemzki.tools.people.db.util;

import com.lemzki.tools.people.db.dto.PersonDTO;
import com.lemzki.tools.util.RecentHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RecentPersons extends RecentHolder<PersonDTO> {
}
