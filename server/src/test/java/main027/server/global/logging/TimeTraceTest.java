//package main027.server.global.logging;
//
//import lombok.extern.slf4j.Slf4j;
//import main027.server.domain.bookmark.service.BookmarkService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.ActiveProfiles;
//
//@Slf4j
//@SpringBootTest
//@ActiveProfiles("stub")
//public class TimeTraceTest {
//
//    @Autowired
//    BookmarkService bookmarkService;
//
//    @Test
//    @DisplayName("TimeTrace AOP가 적용되었는지 로그로 확인 필요")
//    void traceTest() throws InterruptedException {
//        log.info("bookmarkService class={}",bookmarkService.getClass());
//        bookmarkService.findPlaceMemberBookmarked(1L, PageRequest.of(1, 10));
//        Thread.sleep(500);
//    }
//}
