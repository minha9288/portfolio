#include <sys/time.h>
#include <sys/types.h>
#include <unistd.h>

#include <sys/socket.h>
#include <sys/types.h>

#include <netinet/in.h>
#include <arpa/inet.h>

#include <stdio.h>
#include <string.h>

#define MAXLINE 1024
#define PORTNUM 40000
#define SOCK_SETSIZE 1021

int main(int argc, char **argv)
{
	int listen_fd, client_fd;
	socklen_t addrlen;
	int fd_num;
	int maxfd=0;
	int sockfd;
	int i=0;
	char buf[MAXLINE];
	fd_set readfds, allfds;

	int j=0;	//모든 클라이언트에게 전송하기 위한 for문에 사용
	char clientIP[MAXLINE];  //클라이언트의 ip주소를 저장할 변수
	char buffer[MAXLINE];  //클라이언트 ip와 입력받은 buf 내용을 합친 클라이언트에게 보낼 최종 메세지 buffer 변수

	int IPcount = 0;
	

	struct sockaddr_in server_addr, client_addr;

	if((listen_fd = socket(AF_INET, SOCK_STREAM, 0)) == -1)
	{
		perror("socket error");
		return 1;
	}
	memset((void *)&server_addr, 0x00, sizeof(server_addr));
	server_addr.sin_family = AF_INET;
	server_addr.sin_addr.s_addr = htonl(INADDR_ANY);
	server_addr.sin_port = htons(atoi(argv[1]));

	if(bind(listen_fd, (struct sockaddr *)&server_addr, sizeof(server_addr)) == -1)
	{
		perror("bind error");
		return 1;
	}
	if(listen(listen_fd, 5) == -1)
	{
		perror("listen error");
		return 1;
	}
	
	FD_ZERO(&readfds);
	FD_SET(listen_fd, &readfds);

	maxfd = listen_fd;
	while(1)
	{
		allfds = readfds;
		printf("select wait %d\n", maxfd+1);
		
		fd_num = select (maxfd + 1, &allfds, (fd_set *)0, (fd_set *)0, NULL);
		if(FD_ISSET(listen_fd, &allfds))
		{
			addrlen = sizeof(client_addr);
			client_fd = accept(listen_fd, (struct sockaddr *)&client_addr, &addrlen);		

			FD_SET(client_fd, &readfds);

			if(client_fd > maxfd)
				maxfd = client_fd;
			printf("%d가 접속했습니다. accept ok\n", maxfd-3);
			
			strcpy(clientIP, inet_ntoa(client_addr.sin_addr));  //접속한 클라이언트의 ip주소를 clientIP에 저장

			continue;
		}
		

		

		for(i=0 ; i<=maxfd; i++)
		{
			sockfd = i;
			
			if(FD_ISSET(sockfd, &allfds))
			{
				memset(buf, 0x00, MAXLINE);
				if(read(sockfd, buf, MAXLINE) <= 0)
				{
					close(sockfd);
					FD_CLR(sockfd, &readfds);
				}
				else
				{
					if(strncmp(buf, "quit\n", 5)==0)
					{
						close(sockfd);
						FD_CLR(sockfd, &readfds);
						break;
					}
					

					//접속한 클라이언트 모두에게 내용 전송 (클라이언트 4번부터 시작하니깐 4부터 for문)
				     for(j=4; j<=maxfd; j++){

						memset(buffer, 0x00, MAXLINE); //buffer에 내용이 덮어씌워지지 않도록 초기화
						//buffer변수에 클라이언트 ip주소와 buf내용을 저장.	
						sprintf(buffer, "%s님이 보냄 : %s", clientIP, buf); 
						write(j, buffer, strlen(buffer));  //저장된 내용을 클라이언트에게 전송
				     }
				}
				if(--fd_num <= 0)
					break;
			}
		}
	}
}

