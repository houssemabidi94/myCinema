FROM node:16.13.1-alpine3.14 as builder

RUN mkdir -p /app

WORKDIR /app

COPY . .

RUN npm install --verbose

CMD npm start
