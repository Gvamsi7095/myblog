package com.myblog.myblog.Service.Impl;

import com.myblog.myblog.Entity.Post;
import com.myblog.myblog.Exception.ResourceNotFoundException;
import com.myblog.myblog.Payload.PostDto;
import com.myblog.myblog.Payload.PostResponse;
import com.myblog.myblog.Repository.PostRepository;
import com.myblog.myblog.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


   private  PostRepository postRepository;
   private ModelMapper mapper;



    public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
        this.postRepository = postRepository;
       this.mapper=mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post p = postRepository.save(post);
        PostDto dto = mapToDto(p);


        return dto ;
    }

    @Override
    public PostResponse getAllMyId(int pageNo,int pageSize,String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?//it is true u sorted in asc //its true u will sort in ascending///sort ibject shoukd intilazed
                Sort.by(sortBy).ascending()//if condiion i strue run if not use descending order
                :Sort.by(sortBy).descending();
//        Sort sort1=null;
//
//        if(sortDir.equalsIgnoreCase("asc")){
//            sort =Sort.by(sortBy).ascending();
//            //seconfave way to comapre sortiung in above ciode
//        }else {
//            sort=Sort.by(sortBy).descending();
//        }


        PageRequest  pageable = PageRequest.of(pageNo, pageSize,sort);//these methoda over laoded method static method we give pagele


        Page<Post> content = postRepository.findAll(pageable); //this method read only in dependend on page o pagesize
        List<Post> posts = content.getContent();  ///getcontent methos used to convert page of post to list of post
       List<PostDto> dto = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(dto);

        postResponse.setPageNo(content.getNumber());
        postResponse.setPageSize(content.getSize());
        postResponse.setTotalElements(content.getNumberOfElements());
        postResponse.setTotalPages(content.getTotalPages());
        postResponse.setIsLast(content.isLast());



        return postResponse;

    }

    @Override
    public PostDto getById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id",id )//lamdas expression u user
        );
        return mapToDto(post);
}

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById (id).orElseThrow(

                () -> new ResourceNotFoundException("post", "id", id));
post.setTitle(postDto.getTitle());
post.setDescription(postDto.getDescription());
post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);

        PostDto postResponse = mapToDto(updatedPost);

        return  postResponse;
    }

    @Override
    public void deleteById(long id) {
        Post post = postRepository.findById(id).orElseThrow(///this find code out whether  id is found or not
                () -> new ResourceNotFoundException("Post", "id", id)

        );


        postRepository.deleteById(id);
//        Optional<Post> byId = postRepository.findById(id);
//        if(byId.isPresent()){
//            postRepository.deleteById(id);
//        }
//        else{
//            System.out.println("delete is not there");
//
//        }
//

    }

    private Post mapToEntity(PostDto postDto) {


        Post post = mapper.map(postDto, Post.class);
//        Post p = new Post();
//        p.setId(postDto.getId());
//        p.setTitle(postDto.getTitle());
//        p.setDescription(postDto.getDescription());
//        p.setContent(postDto.getContent());
        return post;


    }


    private  PostDto mapToDto(Post p){
        PostDto dto = mapper.map(p, PostDto.class);
//      PostDto dto= new PostDto();
//        dto.setId(save.getId());
//
//         dto.setTitle(save.getTitle());
//        dto.setDescription(save.getDescription());
//         dto.setContent(save.getContent());

       return dto;
    }



}
