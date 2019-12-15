package com.example.controller

import com.example.service.storage.StorageService
import com.example.utils.ResultBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.FileNotFoundException
import java.nio.file.Files


@Controller
@RequestMapping("/files")
class FileUploadController
@Autowired constructor(private val storageService: StorageService) {
  @GetMapping("/{filename:.+}")
  fun serveFile(@PathVariable filename: String?): ResponseEntity<Resource>? {
    if (filename == null || filename == "") {
      return ResponseEntity.badRequest().body(null);
    }
    val file = storageService.load(filename)
    val mimeType = Files.probeContentType(file)
    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_TYPE, mimeType)
        .body<Resource>(FileSystemResource(file))
  }

  @PostMapping("/upload")
  @ResponseBody
  fun handleFileUpload(@RequestParam("file") file: MultipartFile): ResultBean<String> {
    val fileName = storageService.store(file)
    return ResultBean.success(fileName)
  }

  @ExceptionHandler(FileNotFoundException::class)
  fun handleStorageFileNotFound(exc: FileNotFoundException?): ResponseEntity<*>? {
    return ResponseEntity.notFound().build<Any>()
  }


}
