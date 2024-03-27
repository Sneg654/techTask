package com.epam.testproject.service;

import com.epam.testproject.dto.Employee;
import com.epam.testproject.util.PropertiesUtil;

import java.util.*;

public class HierarchyDepthService {
    private List<Employee> employees;
    private static final ResourceBundle messageBundle = ResourceBundle.getBundle("messages");
    public HierarchyDepthService(List<Employee> employees) {
        this.employees = employees;
    }

    public List foundDepthHiearchy() {
        List<String> resultReport = new ArrayList();
        for (Employee e : employees) {
            Set<String> managerChain = new HashSet<>();
            int count = countManagers(e, managerChain, resultReport);
            if (count > PropertiesUtil.NORMAL_DEEP_MANAGER_LIMIT) {
                resultReport.add(String.format(messageBundle.getString("hiearchy.too_much_manager"), e.getId(), e.getFirstName(), e.getSecondName()));
            }
        }
        return resultReport;
    }

    public int countManagers(Employee e, Set<String> managerChain, List<String> result) {
        if (e.getManagerId() == null) {
            return 0;
        } else {
            for (Employee m : employees) {
                if (m.getId().equals(e.getManagerId())) {
                    if (managerChain.contains(m.getId().toString())) {
                        result.add(String.format(messageBundle.getString("hiearchy.cycle_reference"), e.getId()));
                        return 0;
                    }
                    managerChain.add(m.getId().toString());
                    return countManagers(m, managerChain, result) + 1;
                }
            }
        }
        return 0;
    }
}
