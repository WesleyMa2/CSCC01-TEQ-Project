package com.tdat.data.template;

public enum InitialVisitType {
    CPB("Client Profile Bulk"), NARS("Needs Assessment and Referrals Service");

    private String templateName;

    InitialVisitType(String s) {
        this.templateName = s;
    }

    public String getTemplateName() {
        return templateName;
    }

    public static boolean contains(String test) {

        for (InitialVisitType c : InitialVisitType.values()) {
            if (c.getTemplateName().equals(test)) {
                return true;
            }
        }

        return false;
    }
}
