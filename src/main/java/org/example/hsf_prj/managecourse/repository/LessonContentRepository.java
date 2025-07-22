package org.example.hsf_prj.managecourse.repository;

import org.example.hsf_prj.entity.LessonContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonContentRepository extends JpaRepository<LessonContent, Long> {}
