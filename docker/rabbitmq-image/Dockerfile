FROM rabbitmq:management

ADD rabbitmq.conf /etc/rabbitmq/

EXPOSE 5672
EXPOSE 15672

CMD service rabbitmq-server start && /usr/local/bin/rabbitmqadmin declare queue --user=guest --vhost=/ name=spring-boot durable=true && service rabbitmq-server stop && rabbitmq-server
