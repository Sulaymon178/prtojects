package org.example.controller;

import org.example.entitiy.Groups;
import org.example.entitiy.enums.GroupStatus;
import org.example.payload.ApiResponseModel;
import org.example.payload.ReqGroup;
import org.example.payload.ResGroup;
import org.example.repository.GroupsRepository;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public String getGroupPage(Model model) {
        model.addAttribute("groupList", groupsRepository.findAll());
        return "group";
    }

    @ResponseBody
    @GetMapping("/getById")
    public ApiResponseModel getGroupById(@RequestParam UUID id) {
        Groups groups = groupsRepository.findById(id).get();
        ResGroup resGroup = new ResGroup(groups.getId(), groups.getName(), groups.getGroupStatus());
        return new ApiResponseModel("success", true, resGroup);
    }


    @PostMapping
    public String saveGroup(@ModelAttribute ReqGroup reqGroup) {
        if (reqGroup.getId() == null) {
            groupsRepository.save(new Groups(reqGroup.getName(), GroupStatus.valueOf(reqGroup.getGroupStatus())));
        } else {
            Groups groups = groupsRepository.findById(reqGroup.getId()).get();
            groups.setGroupStatus(GroupStatus.valueOf(reqGroup.getGroupStatus()));
            groups.setName(reqGroup.getName());
            groupsRepository.save(groups);
        }
        return "redirect:/group";

    }

    @Transactional
    @ResponseBody
    @DeleteMapping("{id}")
    public ApiResponseModel deleteGroupById(@PathVariable UUID id) {
        try {
            studentRepository.deleteAllByGroupsId(id);
            groupsRepository.deleteById(id);
            return new ApiResponseModel("deleted", true, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponseModel("Error in deleting", false, null);
        }


    }
}
