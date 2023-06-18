provider "google" {
  project = "K8B00929835"
  region  = "northamerica-northeast1"
}

resource "google_container_cluster" "b00929835k8cluster" {
  name               = "b00929835k8cluster"
  location           = "northamerica-northeast1"
  remove_default_node_pool = true

  initial_node_count = 1

  master_auth {
    username = ""
    password = ""

    client_certificate_config {
      issue_client_certificate = false
    }
  }

  node_config {
    preemptible  = false
    machine_type = "e2-micro"
    disk_size_gb = 10
    boot_disk_type = "pd-standard"
    image_type = "COS_CONTAINERD"
  }
}
