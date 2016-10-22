#include <sys/socket.h>
#include <arpa/inet.h>
#include <sys/stat.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#define MAXLINE 1024

int main(int argc, char **argv)
{
	struct sockaddr_in serveraddr;
	int server_sockfd;
	int client_len;
	char buf[MAXLINE];
	int maxfd1;
	fd_set read_fds;
	
	/*포트번호 입력 안하면 에러메세지 출력*/
	if(argc!=2)
	{
		printf("Use : %s [port]\n", argv[0]);
		return 1;
	}
	
	if ((server_sockfd = socket(AF_INET, SOCK_STREAM, 0)) == -1)
	{
		perror("error :");
		return 1;
	}

	/*연결 요청할 서버의 주소와 포트번호 프로토콜 등을 지정한다. */
	server_sockfd = socket(AF_INET, SOCK_STREAM, 0);
	serveraddr.sin_family = AF_INET;
	serveraddr.sin_addr.s_addr = inet_addr("172.31.142.250");
	serveraddr.sin_port = htons(atoi(argv[1]));
	
	client_len = sizeof(serveraddr);
	
	/*서버에 연결을 시도한다. */
	if (connect(server_sockfd, (struct sockaddr *)&serveraddr, client_len) == -1)
	{
		perror("connect error :");
		return 1;
	}

	maxfd1 = server_sockfd + 1;

	while(1)  //데이터입력을 한번만 하지 않고 계속해서 하기위해 while문으로 변경 
     {
     	//서버로부터 오는 데이터를 계속 받기위해 클라이언트 코드에서도 select 이용
       	FD_SET(0, &read_fds);
       	FD_SET(server_sockfd, &read_fds);
 
	     if(select(maxfd1, &read_fds, (fd_set *)0, (fd_set *)0, (struct timeval *)0) < 0)
          {
          	fprintf(stderr, "select error <= 0\n");
          	return(0);
         	}
 
         	if(FD_ISSET(server_sockfd, &read_fds))
         	{	
          	int size;
          	//클라이언트로부터 읽을 테이터가 있으면 데이터 출력
               if((size = recv(server_sockfd, buf, MAXLINE, 0)) > 0)
              	{
              		buf[size] = '\0';
                 	printf("%s",buf);
              	}
         	}
 
         	if(FD_ISSET(0, &read_fds))
         	{
         		if(fgets(buf, MAXLINE, stdin))
         		{
         			//입력 받은 데이터를 서버로 전송한다.
				if (write(server_sockfd, buf, MAXLINE) <= 0) 
				{
					perror("write error :");
					return 1;
				}
			}
         	}
     }//while
	close(server_sockfd);
}


