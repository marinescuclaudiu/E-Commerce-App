package com.cmarinescu.backend.olfactoryGroup.service;

import com.cmarinescu.backend.olfactoryGroup.model.OlfactoryGroup;

import java.util.List;

public interface IOlfactoryGroupService {
    OlfactoryGroup addOlfactoryGroup(OlfactoryGroup olfactoryGroup);
    List<OlfactoryGroup> getAllOlfactoryGroups();
}
