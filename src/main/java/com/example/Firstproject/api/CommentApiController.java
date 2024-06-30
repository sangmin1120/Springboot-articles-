package com.example.Firstproject.api;

import com.example.Firstproject.DTO.CommentDto;
import com.example.Firstproject.entity.Comment;
import com.example.Firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // 서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleId);

        // 결과 응답
        return (dtos != null) ?
                ResponseEntity.status(HttpStatus.OK).body(dtos):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId , @RequestBody CommentDto dto){
        CommentDto commentDto = commentService.create(articleId,dto);
        return (commentDto != null)?
                ResponseEntity.status(HttpStatus.OK).body(commentDto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id , @RequestBody CommentDto dto){
        // 서비스 위임
        CommentDto updated = commentService.update(id,dto);
        // 반환
        return (updated != null)?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        // 서비스 위임
        CommentDto deleted = commentService.delete(id);

        // 반환
        return (deleted != null)?
                ResponseEntity.status(HttpStatus.OK).body(deleted):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
