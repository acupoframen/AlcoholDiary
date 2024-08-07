package hwanchoe.spbootdiary.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import hwanchoe.spbootdiary.domain.Post;
import hwanchoe.spbootdiary.domain.QPost;
import hwanchoe.spbootdiary.domain.QReply;
import hwanchoe.spbootdiary.dto.PostListReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class PostSearchImpl extends QuerydslRepositorySupport implements PostSearch {
    public PostSearchImpl(){
        super(Post.class);
    }

    @Override
    public Page<Post> search1(Pageable pageable) {
        QPost post = QPost.post;
        JPQLQuery<Post> query = from(post);
        query.where(post.title.contains("1"));

        List<Post> list = query.fetch();
        long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<Post> searchAll(String[] types, String keyword, Pageable pageable){
        QPost post = QPost.post;
        JPQLQuery<Post> query = from(post);
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (types != null && types.length > 0 && keyword != null){
            for (String type : types){
                switch (type){
                    case "t":
                        booleanBuilder.or(post.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(post.detail.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(post.user.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        query.where(post.id.gt(0));
        this.getQuerydsl().applyPagination(pageable, query);
        List<Post> list = query.fetch();
        long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page <PostListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable){
        QPost post=QPost.post;
        QReply reply=QReply.reply;
        JPQLQuery<Post> query = from(post);
        query.leftJoin(reply).on(reply.post.eq(post));
        query.groupBy(post);
        if (types != null && types.length > 0 && keyword != null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types){
                switch (type){
                    case "t":
                        booleanBuilder.or(post.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(post.detail.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(post.user.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        query.where(post.id.gt(0));
        JPQLQuery<PostListReplyCountDTO> dtoQuery=query.select(Projections.bean(PostListReplyCountDTO.class,
                post.id,post.title,post.user,post.regDate,reply.count().as("replyCount")));
        this.getQuerydsl().applyPagination(pageable,dtoQuery);
        List<PostListReplyCountDTO> dtoList=dtoQuery.fetch();
        long count=dtoQuery.fetchCount();
        return new PageImpl<>(dtoList,pageable,count);
    }

}
