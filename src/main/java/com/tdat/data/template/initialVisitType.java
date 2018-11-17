package com.tdat.data.template;

public enum initialVisitType {
    CPB("Client Profile Bulk"), NARS("Needs Assesment & Referral Service");

    private String templateName;

    initialVisitType(String s) {
        this.templateName = s;
    }

    public String getTemplateName() {
        return templateName;
    }
}
