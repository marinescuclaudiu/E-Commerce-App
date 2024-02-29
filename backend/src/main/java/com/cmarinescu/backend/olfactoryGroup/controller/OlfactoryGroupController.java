package com.cmarinescu.backend.olfactoryGroup.controller;

import com.cmarinescu.backend.olfactoryGroup.dto.CreateOlfactoryGroupRequest;
import com.cmarinescu.backend.olfactoryGroup.dto.OlfactoryGroupResponse;
import com.cmarinescu.backend.olfactoryGroup.mapper.OlfactoryGroupMapper;
import com.cmarinescu.backend.olfactoryGroup.model.OlfactoryGroup;
import com.cmarinescu.backend.olfactoryGroup.service.OlfactoryGroupService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OlfactoryGroupController {
    private OlfactoryGroupService olfactoryGroupService;
    private OlfactoryGroupMapper olfactoryGroupMapper;

    public OlfactoryGroupController(OlfactoryGroupService olfactoryGroupService, OlfactoryGroupMapper olfactoryGroupMapper) {
        this.olfactoryGroupService = olfactoryGroupService;
        this.olfactoryGroupMapper = olfactoryGroupMapper;
    }

    @PostMapping("admin/olfactory-group")
    public ResponseEntity<OlfactoryGroupResponse> addOlfactoryGroup(@Valid @RequestBody CreateOlfactoryGroupRequest request){
        OlfactoryGroup olfactoryGroup = olfactoryGroupMapper.requestToModel(request);
        OlfactoryGroupResponse response = olfactoryGroupMapper.modelToResponse(olfactoryGroupService.addOlfactoryGroup(olfactoryGroup));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("public/olfactory-group")
    public ResponseEntity<List<OlfactoryGroupResponse>> getAllOlfactoryGroups(){
        List<OlfactoryGroup> olfactoryGroupList = olfactoryGroupService.getAllOlfactoryGroups();
        List<OlfactoryGroupResponse> responseList = olfactoryGroupMapper.modelListToResponseList(olfactoryGroupList);
        return ResponseEntity.ok().body(responseList);
    }
}
