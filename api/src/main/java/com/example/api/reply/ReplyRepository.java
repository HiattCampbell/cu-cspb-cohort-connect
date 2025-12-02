package com.example.api.reply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByBulletin_Id(Long bulletinId);
}