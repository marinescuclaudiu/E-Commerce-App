package com.cmarinescu.backend.olfactoryGroup.service;

import com.cmarinescu.backend.olfactoryGroup.repository.OlfactoryGroupRepository;
import com.cmarinescu.backend.olfactoryGroup.model.OlfactoryGroup;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OlfactoryGroupService implements IOlfactoryGroupService{
    private OlfactoryGroupRepository olfactoryGroupRepository;

    public OlfactoryGroupService(OlfactoryGroupRepository olfactoryGroupRepository) {
        this.olfactoryGroupRepository = olfactoryGroupRepository;
    }

    @Transactional
    public OlfactoryGroup addOlfactoryGroup(OlfactoryGroup olfactoryGroup) {
        return olfactoryGroupRepository.save(olfactoryGroup);
    }

    public List<OlfactoryGroup> getAllOlfactoryGroups() {
        return olfactoryGroupRepository.findAll();
    }
}
