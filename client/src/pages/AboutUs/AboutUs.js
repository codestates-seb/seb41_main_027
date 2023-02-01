import styled from 'styled-components'

import Nav from '../../components/Nav/Nav'
import Header from '../../components/Header/Header'
import TabAboutUs from '../AboutUs/TabAboutUs'

const Wrapper = styled.div`
  width: 100%;
`
const Container = styled.section`
  z-index: 1000;
  overflow: auto;
  height: calc(100% - 88px);
  padding: 40px;
  gap: 64px;
  display: flex;
  flex-direction: column;
  border-radius: 32px 0px 0px 0px;
  box-shadow: -8px -4px 30px rgba(0, 129, 76, 0.4);
  background-color: #fff;
`
const Content = styled.div`
  display: flex;
  flex-direction: column;
  gap: 16px;
  color: #5d5f61;
  letter-spacing: -0.8px;

  // cmm shadow & border
  .content-box,
  .service-img {
    border: 1px solid #b8bccf;
    border-radius: 24px;
    box-shadow: 0px 2px 10px rgba(25, 1, 52, 0.12);
  }
  .wrap-content {
    display: flex;
    flex-direction: column;
    gap: 24px;
  }
  h2 {
    font-size: 26px;
    font-weight: 700;
  }
  .wrap-img {
    display: flex;
    gap: 24px;
    width: 100%;
    .service-img {
      width: calc(100% / 3 - 16px);
      height: 282px;
      /* object-fit: cover; */
    }
  }
  .content-box {
    padding: 24px 32px;
    box-sizing: border-box;
    width: 100%;
  }
  .content-tit {
    margin-bottom: 12px;
    font-size: 22px;
    font-weight: 700;
  }
  .content-body {
    font-size: 18px;
    font-weight: 400;
    line-height: 28px;
    span {
      font-weight: 700;
      padding-right: 8px;
    }
  }
`

const AboutUs = () => {
  return (
    <>
      <Nav />
      <Wrapper>
        <Header />
        <Container>
          <Content>
            <h2>어떤 서비스를 만들고 있나요?</h2>
            <div className="wrap-content">
              <div className="wrap-img">
                <img
                  className="service-img"
                  src="https://user-images.githubusercontent.com/48042650/215324303-0d5000bb-583d-443d-a5ef-5dbb74b02a3f.png"
                  alt="서비스 이미지"
                />
                <img
                  className="service-img"
                  src="https://user-images.githubusercontent.com/48042650/215324332-c5a5de8b-3488-4d2a-9f68-76c92ba3ff58.png"
                  alt="서비스 이미지"
                />
                <img
                  className="service-img"
                  src="https://user-images.githubusercontent.com/111366402/215396766-e421659b-38df-4fe7-be14-b6fe34307c2c.png"
                  alt="서비스 이미지"
                />
              </div>
              <div className="content-box">
                <p className="content-tit">우리가 함께 그리는 에코그린 서울</p>
                <p className="content-body">
                  에코그린서울(EchoGreenSeoul)은 서울과 인근 지역에 있는 친환경 관련 장소를 공유하는 사이트입니다.
                  <br />
                  여기서 Echo는 내가 알고있는 좋은 장소를 메아리쳐서 함께 공유하자는 의미입니다.
                  <br />
                  <br />
                  우리가 함께 그리는 서울 친환경 지도!, 에코그린서울에서 여러분을 기다리고 있습니다. <br />
                  여러분들의 장소를 저희에게 공유해주세요! 당신의 이야기로 서울을 아름답게 가꿀 수 있습니다.
                  <br />
                  <br />
                  지금 내가 알고있는 좋은 장소들을 등록하여 함께 공유하고 리뷰를 남겨보세요. 😃
                </p>
              </div>
            </div>
          </Content>
          <Content>
            <h2>크루들의 한마디</h2>
            <p className="content-body">서비스를 이용하는 환경지킴이들에게 전하는 메세지에요.</p>
            <TabAboutUs />
          </Content>
          <Content>
            <h2>Contact</h2>
            <p className="content-body">제안문의를 보내주시면 담당자가 확인 후 연락드리겠습니다.</p>
            <div className="content-box">
              <p className="content-body">
                <span>이메일 </span>
                <a href="https://mail.google.com/mail/?view=cm&amp;fs=1&amp;to=echogreenseoul@gmail.com">
                  echogreenseoul@gmail.com
                </a>
                <br />
                <span>깃허브 </span>github.com/codestates-seb/seb41_main_027
              </p>
            </div>
          </Content>
        </Container>
      </Wrapper>
    </>
  )
}

export default AboutUs
