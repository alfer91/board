package org.zerock.board.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.QBoard;
import org.zerock.board.entity.QReply;

import java.util.List;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board serach1() {

        log.info("search1............................");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));
        jpqlQuery.select(board).where(board.bno.eq(2L));

        log.info("--------------------------");
        log.info(jpqlQuery);
        log.info("--------------------------");

        List<Board> result = jpqlQuery.fetch();

        result.forEach(li -> {
            log.info(li);
        });

        return null;
    }
}
