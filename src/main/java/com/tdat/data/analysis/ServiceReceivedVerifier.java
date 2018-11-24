package com.tdat.data.analysis;

import com.tdat.data.ColumnNotFoundException;
import com.tdat.data.MasterData;
import com.tdat.data.TableData;
import com.tdat.data.VisitData;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to verify whether users of the agencies are receiving the services they are referred
 */
public class ServiceReceivedVerifier {

    private static final String COMMUNITY = "Community Connections";
    private static final String INFO = "Information and Orientation";
    private static final String EMPLOYMENT = "Employment Related Services";
    private static final String LT_ENROLL = "Language Training - Client Enrolment";
    private static final String UID = "Unique Identifier Value";
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

    // TODO: 2018-11-23 REMOVE
    public static void test() throws ColumnNotFoundException {
        System.out.println("[test] " + getReferralsCount());
        System.out.println("[test] " +
            checkServiceReceived(MasterData.initialVisitData.getVisitsData().get(0)));
    }

    /**
     * Scans initialData, and gets the count for all the services that have been referred
     */
    public static Map<String, Integer> getReferralsCount() {
        Map<String, Integer> result = new HashMap<>();
        List<VisitData> initialVisitData = MasterData.initialVisitData.getVisitsData();

        for (VisitData visit : initialVisitData) {
            for (String referralType : associations.keySet()) {
                String referralStatus = visit.getColumnData(referralType).trim();
                if (referralStatus.equals("Yes")) {
                    if (!result.containsKey(referralType)) {
                        result.put(referralType, 0);
                    }
                    result.put(referralType, result.get(referralType) + 1);
                }
            }
        }
        return result;
    }

    private static boolean wasPersonReferredService(VisitData visit, String service) {
        return visit.getColumnData(service).trim().equals("Yes");
    }

    private static List<String> getAllServicesOfTemplate(String template) {
        List<String> result = new ArrayList<>();
        for (String service : associations.keySet()) {
            if (associations.get(service).equals(template)) {
                result.add(service);
            }
        }
        return result;
    }

    /**
     * Returns if a service that was referred was actually received for a specific person
     */
    public static Map<String, Integer> checkServiceReceived(VisitData person)
        throws ColumnNotFoundException {
        String currPersonID = person.getColumnData(UID);
        Map<String, Integer> servicesReceived = new HashMap<>();

        for (String serviceReferred : associations.keySet()) {
            servicesReceived.put(serviceReferred, 0);
            if (!wasPersonReferredService(person, serviceReferred)) {
                continue;
            }

            for (Year year : MasterData.serviceProvidedData.keySet()) {
                TableData dataForCurrYear = MasterData.getYearData(year);
                SingleTableReader reader = new SingleTableReader(dataForCurrYear);
                List<VisitData> personVisits = reader.selectWhereColEquals(UID, currPersonID);
                for (VisitData visit : personVisits) {
                    String visitTemplate = visit.getColumnData("Template");
                    String targetTemplate = associations.get(serviceReferred);
                    if (visitTemplate.equals(targetTemplate)) {
                        servicesReceived.put(serviceReferred, 1);
                    }
                }
            }

        }
        return servicesReceived;
    }


}

