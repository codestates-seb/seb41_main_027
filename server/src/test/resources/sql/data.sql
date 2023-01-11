insert into emoji (name)
values ('웃음'),
       ('행복함'),
       ('사랑'),
       ('침나옴'),
       ('궁금함'),
       ('무표정'),
       ('싫음'),
       ('엑스');

insert into category (name)
values ('제로웨이스트샵'),
       ('공방'),
       ('푸드'),
       ('카페');

insert into member (nick_name, email)
values ('새좋아요', 'mjehu1@upenn.edu'),
       ('에코홀릭', 'smcevay2@tinypic.com'),
       ('서울바이크', 'ehamly3@dagondesign.com'),
       ('공방매니아', 'rbeckingham4@jigsy.com'),
       ('환경보호가', 'bearey0@furl.net'),
       ('카페매니아', 'rkensington5@myspace.com');

insert into place (member_id, category_id, name, address, description, kakao_id, latitude, longitude)
values (1, 1, '두레생협(미아점)', '서울특별시 강북구 삼양로27길 46', '친환경 생필품, 리필세제, 과일등을 판매합니다', 27351994, 0, 0),
       (1, 1, '열매네살림', '서울특별시 강북구 삼양로27길 19', '소분 제품 및 리필용 제품 판매,제로웨이스트 관력 책 대여 등 여러가지 제품을 취급합니다.', 2, 0, 0),
       (1, 1, '지구랑가게', '서울특별시 노원구 광운로 53', '세제 무포장 리필, 플라스틱 업사이클링 제품을 제공합니다.', 3, 0, 0),
       (1, 1, '행복중심생협(공릉점)', '서울특별시 노원구 공릉로34길 23', '우유팩으로 만든 휴지, 생고무장갑 등을 판매하며 친환경먹거리 수업이 있습니다.', 4, 0, 0),
       (1, 1, '물푸레지구살림', '서울특별시 은평구 진관4로 48-51', '밀랍랩, 대나무칫솔, 고체향수 등 친환경 제품을 판매합니다.', 5, 0, 0);