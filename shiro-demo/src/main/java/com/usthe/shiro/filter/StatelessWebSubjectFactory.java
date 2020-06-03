package com.usthe.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

public class StatelessWebSubjectFactory  extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        context.setSessionCreationEnabled(Boolean.FALSE);
        return super.createSubject(context);
    }

    public StatelessWebSubjectFactory() {}
}
