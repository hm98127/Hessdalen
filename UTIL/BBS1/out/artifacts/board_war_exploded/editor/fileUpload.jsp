<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.io.*" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    if (ServletFileUpload.isMultipartContent(request)) {
        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        //UTF-8 인코딩 설정
        uploadHandler.setHeaderEncoding("UTF-8");
        List<FileItem> items = uploadHandler.parseRequest(request);
        String realname = "";
        Long size = 0L;

        // java 날짜 class
        LocalDate date = LocalDate.now();
        String datePath = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String savePath = datePath.replace("-", "/");

        // 실서버, 로컬테스트 구분하여 업로드 경로 설정
        String nowUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "/");
        String uploadPath = nowUrl.indexOf("localhost") > 1 ?
                request.getServletContext().getRealPath("upload") : "/var/upload";

        //각 필드태그들을 FOR문을 이용하여 비교를 합니다.
        for (FileItem item : items) {
            //image.html 에서 file 태그의 name 명을 "image_file"로 지정해 주었으므로
            if (item.getFieldName().equals("image_file")) {
                if (item.getSize() > 0) {
                    String ext = item.getName().substring(item.getName().lastIndexOf(".") + 1).toLowerCase();
                    // 파일 형식 검사
                    if (!(ext.equals("jpg") || ext.equals("png") || ext.equals("gif") || ext.equals("jpeg") || ext.equals("bmp") ||
                            ext.equals("JPG") || ext.equals("PNG") || ext.equals("GIF") || ext.equals("JPEG") || ext.equals("BMP"))) {
                        return;
                    }
                    //파일 기본경로 _ 상세경로
                    String path = uploadPath + File.separator + savePath + File.separator;

                    try {
                        //디렉토리 존재하지 않을경우 디렉토리 생성
                        if (!new File(path).exists()) {
                            new File(path).mkdirs();
                        }
                        //서버에 업로드 할 파일명(한글문제로 인해 원본파일은 올리지 않는것이 좋음)
                        realname = UUID.randomUUID().toString() + "." + ext;
                        size = item.getSize();
                        ///////////////// 서버에 파일쓰기 /////////////////
                        InputStream is = item.getInputStream();
                        OutputStream os = new FileOutputStream(path + realname);
                        int numRead;
                        byte b[] = new byte[(int) item.getSize()];
                        while ((numRead = is.read(b, 0, b.length)) != -1) {
                            os.write(b, 0, numRead);
                        }
                        if (is != null) is.close();
                        os.flush();
                        os.close();
                        ///////////////// 서버에 파일쓰기 /////////////////
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter pw = response.getWriter();

        //json string 값으로 callback
        //json 값으로 넘기는 필요 값
        //imageurl, filename,filesize,imagealign
        nowUrl = nowUrl.replace("://office.", "://www.") + "upload/";

        String url = nowUrl + savePath + "/" + realname;
        pw.print("{\"imageurl\" : \"" + url + "\",\"filename\":\"" + realname + "\",\"filesize\": 600,\"imagealign\":\"C\"}");
        pw.flush();
        pw.close();
    }
%>
