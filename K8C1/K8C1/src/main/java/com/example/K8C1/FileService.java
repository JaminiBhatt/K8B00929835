package com.example.K8C1;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    private final Storage storage;

    public FileService() {
        this.storage = StorageOptions.getDefaultInstance().getService();
    }

    public boolean storeFile(String fileName, String fileData) {
        try {
            BlobId blobId = BlobId.of("k8-b00929835", fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            Blob blob = storage.create(blobInfo, fileData.getBytes());
            return blob != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
