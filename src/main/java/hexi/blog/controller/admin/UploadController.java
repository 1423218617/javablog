package hexi.blog.controller.admin;


import hexi.blog.dao.AttachDao;
import hexi.blog.model.pojo.Attach;
import hexi.blog.model.vo.ResultVo;
import hexi.blog.utils.DateUtil;
import hexi.blog.utils.PathUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("admin/attach")
public class UploadController {
    @Autowired
    private AttachDao attachDao;

    private static final String CLASSPATH= PathUtil.getUplodFilePath()+"/src/main/resources/static/upload/";

    @GetMapping
    public String index(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "12") int limit){
        Page<Attach> attachs=attachDao.findAll(new PageRequest(page-1,limit));
        request.setAttribute("attachs",attachs);
        attachs.getContent().size();
        return "admin/attach";

    }

    @PostMapping(value = "upload")
    @ResponseBody
    public ResultVo upload(HttpServletRequest request, @RequestParam("file")MultipartFile[] multipartFiles)throws IOException {
        for (MultipartFile multipartFile : multipartFiles) {
            Attach attach=new Attach();
            String path=CLASSPATH+multipartFile.getOriginalFilename();
            byte[] fileBytes=multipartFile.getBytes();
            new FileOutputStream(path).write(fileBytes);
            attach.setFname(multipartFile.getOriginalFilename());
            attach.setFkey(multipartFile.getOriginalFilename());
            attach.setFtype(StringUtils.substring(multipartFile.getOriginalFilename(),multipartFile.getOriginalFilename().lastIndexOf(".")+1));
            attach.setCreated(DateUtil.getUnixTime());
            attachDao.save(attach);
            System.out.println(attach);
        }
        return new ResultVo(true,"上传文件成功");
    }
    @GetMapping (value = "upload")
    @ModelAttribute
    public void out(@RequestParam String fkey, HttpServletResponse response)throws  IOException{
        byte[] bytes=new byte[2048];
        new FileInputStream(CLASSPATH+fkey).read(bytes);
        System.out.println(CLASSPATH+fkey);
        response.getOutputStream().write(bytes);
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultVo delete(@RequestParam Integer id){
        String path=CLASSPATH+attachDao.getOne(id).getFkey();
        new File(path).delete();
        attachDao.deleteById(id);
        return new ResultVo(true,"删除成功");
    }
}
