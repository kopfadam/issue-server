package com.kodstar.backend.controller;

import com.kodstar.backend.model.dto.BatchDeleteRequest;
import com.kodstar.backend.model.dto.Issue;
import com.kodstar.backend.model.dto.Label;
import com.kodstar.backend.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collection;


@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
public class IssueController {

    private final IssueService issueService;

    @GetMapping("/issue/{id}")
    public ResponseEntity<Issue> getIssueById(@Valid @PathVariable Long id){

        return ResponseEntity.ok(issueService.findById(id));
    }

    @GetMapping("/issues")
    public ResponseEntity<Collection<Issue>> getIssues(){
        Collection<Issue> issues = issueService.getAllIssues();

        if (issues.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(issues);
    }

    @PostMapping("/issue")
    public ResponseEntity<Issue> createIssue(@Valid @RequestBody Issue issue){

        return new ResponseEntity(issueService.saveIssueEntity(issue), HttpStatus.CREATED);
    }

    @PutMapping("/issue/{id}")
    public ResponseEntity<Issue> updateIssue(@Valid @PathVariable Long id, @RequestBody Issue issue){

        return ResponseEntity.ok(issueService.updateIssueEntity(id,issue));

    }

 
    @DeleteMapping("/issue/{id}")
    public ResponseEntity<Void> deleteIssue(@Valid @PathVariable Long id){

        issueService.deleteIssue(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/issues/labels")
    public ResponseEntity<Collection<Label>> getAllLabels(){

        Collection<Label> labels = issueService.getAllLabels();

        return ResponseEntity.ok(labels);
    }

    @PostMapping("/issues/batch")
    public ResponseEntity<Void> deleteMultipleIssues(@Valid @RequestBody BatchDeleteRequest request){

        issueService.deleteMultipleIssues(request);

        return ResponseEntity.noContent().build();
    }
}
