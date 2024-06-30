package com.example.Firstproject.service;

import com.example.Firstproject.DTO.CommentDto;
import com.example.Firstproject.entity.Article;
import com.example.Firstproject.entity.Comment;
import com.example.Firstproject.repository.ArticleRepository;
import com.example.Firstproject.repository.CommentRepository;
import jakarta.transaction.Transactional;
import jdk.jfr.StackTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId){
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        // 엔티티를 DTO로 변환
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (int i=0;i<comments.size();i++){
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }

        return commentRepository.findByArticleId(articleId)
                .stream().map(comment -> CommentDto.createCommentDto(comment)).collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // article 찾기
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! " + " 대상 게시글이 없습니다"));

        // dto->엔티티 변환
        Comment comment = Comment.create(dto,article);

        // 생성
        Comment created = commentRepository.save(comment);

        // 변환
        return CommentDto.createCommentDto(created);

    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!" + "대상 댓글이 없습니다."));

        // 댓글 수정
        target.patch(dto);

        // DB로 갱신
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 엔티티 찾아 , 예외
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 댓글을 몾 찾았습니다."));

        // 삭제
        commentRepository.delete(target);

        return CommentDto.createCommentDto(target);
    }
}
