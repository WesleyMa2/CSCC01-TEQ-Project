package com.tdat.data.analysis;

import com.tdat.data.MasterData;
import com.tdat.data.VisitData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to verify whether users of the agencies are receiving the services they are referred
 */
public class ServiceRecievedVerifier {

    private static final String COMMUNITY = "Community Connections";
    private static final String INFO = "Information and Orientation";
    private static final String EMPLOYMENT = "Employment Related Services";
    private static final String LT_ENROLL = "Language Training - Client Enrolment";
    private static final Map<String, String> associations;

    static {
        associations = new HashMap<>();
        associations.put("Community Services Referrals", COMMUNITY);
        associations.put("Education/Skills Development Referrals", COMMUNITY);
        associations.put("Employment-related Referrals", EMPLOYMENT);
        associations.put("Family Support Referrals", COMMUNITY);
        associations.put("Financial Referrals", COMMUNITY);
        associations.put("Find employment Referrals", EMPLOYMENT);
        associations.put("Health/Mental Health/Well Being Referrals", COMMUNITY);
        associations.put("Housing/Accommodation Referrals", COMMUNITY);
        associations.put("Improve Other Skills Referrals", INFO);
        associations.put("Language (Non-IRCC) Referrals", LT_ENROLL);
        associations.put("Legal Information and Services Referrals", INFO);
        associations.put("Improve Language Skills Referrals", LT_ENROLL);
    }

    private boolean isValidService(String service) {
        if (!associations.containsKey(service)) {
            return false;
        }
        return true;
    }

    /**
     * Scans initialData, and gets the count for all the services that have been referred
     */
    public Map<String, Integer> getReferralsCount() {
        Map<String, Integer> result = new HashMap<>();
        List<VisitData> initialVisitData = MasterData.initialVisitData.getVisitsData();

        for (VisitData visit : initialVisitData) {
            for (String referralType : associations.keySet()) {
                if (visit.getColumnData(referralType).equals("YES")) {
                    if (!result.containsKey(referralType)) {
                        result.put(referralType, 0);
                    }
                    result.put(referralType, result.get(referralType) + 1);
                }
            }
        }

        return result;
    }

}
