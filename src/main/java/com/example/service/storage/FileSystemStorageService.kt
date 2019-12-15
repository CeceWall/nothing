package com.example.service.storage

import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import java.util.stream.Stream
import javax.annotation.PostConstruct


@Service
@PropertySource("classpath:storage.properties")
class FileSystemStorageService : StorageService {
  @Value("\${storage.file.path}")
  private lateinit var filePath: String;

  private lateinit var directory: Path

  @PostConstruct
  override fun init() {
    val directory = Paths.get(this.filePath);

    if (!Files.exists(directory)) {
      Files.createDirectories(directory)
    } else if (!Files.isDirectory(directory)) {
      throw IllegalStateException("${this.filePath}文件已存在且不是目录")
    }
    this.directory = directory;
  }

  override fun store(file: MultipartFile): String {
    val ext = FilenameUtils.getExtension(file.originalFilename) // returns "exe"
    val randomFileName = this.directory.resolve(UUID.randomUUID().toString() + ".${ext}")
    val tempFile = Files.createFile(randomFileName)
    file.transferTo(tempFile)
    return tempFile.fileName.toString()
  }

  override fun loadAll(): Stream<Path> {
    throw NotImplementedError("")
  }

  override fun load(filename: String): Path {
    return this.directory.resolve(filename)
  }

  override fun loadAsResource(filename: String): Resource {
    return FileSystemResource(this.load(filename))
  }

  override fun deleteAll() {
    if (Files.exists(this.directory) && Files.isDirectory(this.directory)) {
      FileUtils.cleanDirectory(this.directory.toFile());
    }
  }

}
