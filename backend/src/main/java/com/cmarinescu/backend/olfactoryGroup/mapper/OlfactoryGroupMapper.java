package com.cmarinescu.backend.olfactoryGroup.mapper;

import com.cmarinescu.backend.olfactoryGroup.dto.CreateOlfactoryGroupRequest;
import com.cmarinescu.backend.olfactoryGroup.dto.OlfactoryGroupResponse;
import com.cmarinescu.backend.olfactoryGroup.model.OlfactoryGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OlfactoryGroupMapper {
    public OlfactoryGroup requestToModel(CreateOlfactoryGroupRequest request) {
        return new OlfactoryGroup(
                request.getName(),
                request.getDescription());
    }

    public OlfactoryGroupResponse modelToResponse(OlfactoryGroup olfactoryGroup) {
        return new OlfactoryGroupResponse(
                olfactoryGroup.getId(),
                olfactoryGroup.getName(),
                olfactoryGroup.getDescription());
    }

    public List<OlfactoryGroupResponse> modelListToResponseList(List<OlfactoryGroup> olfactoryGroupList) {
        List<OlfactoryGroupResponse> responseList = new ArrayList<>();

        for(OlfactoryGroup olfactoryGroup : olfactoryGroupList){
            responseList.add(modelToResponse(olfactoryGroup));
        }

        return responseList;
    }
}
