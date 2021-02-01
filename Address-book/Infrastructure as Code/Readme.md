1. Setup a Jenkins Master:
2. Setup Nexus Server: (Store docker images, java, nodejs, python dependencies and release artifacts)
3. Configure Jenkins with jobs to each Repository with CI to define build stability.
4. Terraform Script to Create EC2 Server(Jenkins/Nexus), Security Groups, ECS Cluster(Application Services) configured with pulling the docker images from the Nexus, Load Balancer with Auto Scaling, Route 53/DNS, RDS Setup(Relational/No-Sql Databases) considering a VPC environment with defined subnets with appropriate security groups.
5. All the Application Services are configured with Datadog agent which gives metrics around health of the server to application.
6. Load Balancer with auto-scaling maintains scalability with the load on the application.
7. By configuring the load balancer with security groups, only certain endpoints will be visible to customers which makes it secured.
8. Configuring the databases across multiple regions/availability zones, data will always be reliable which in turn result in an reliable application.
9. Configuring Datadog with Services will result in profiling the logs with errors and handling them appropriately on Application Team.
10. Remove the usage of passwords in the code by adding Hashicorp Vault, which will be the best case scenario of least privileges.





