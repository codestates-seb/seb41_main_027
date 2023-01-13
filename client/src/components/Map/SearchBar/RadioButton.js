import styled from 'styled-components'

const Wrapper = styled.div`
  *,
  *:after,
  *:before {
    box-sizing: border-box;
  }

  input[type='radio'] {
    display: none;
  }

  .tabs {
    /* z-index: 10; */
    position: relative;
    display: flex;
    width: 160px;
    height: 48px;
    padding: 8px;
    /* box-sizing: border-box; */
    border: 1px solid #d2d5e1;
    border-radius: 16px;
    background-color: #fff;
    box-shadow: 0 0 1em 0 rgba(0, 0, 0, 0.2);
    /* box-shadow: 0 0 1px 0 rgba(#185ee0, 0.15), 0 6px 12px 0 rgba(#185ee0, 0.15); */
  }

  .tab {
    z-index: 10;
    /* position: relative; */
    display: flex;
    align-items: center;
    justify-content: center;
    /* box-sizing: border-box; */
    width: 80px;
    height: calc(48px - 16px);
    font-size: 18px;
    font-weight: 500;
    border-radius: 99px;
    cursor: pointer;
    transition: color 0.15s ease-in;
  }

  input[type='radio'] {
    &:checked {
      & + label {
        color: #31b679;
        /* background-color: #e8fdf4;
        border-radius: 8px; */
      }
    }
  }

  input[id='Newest'] {
    &:checked {
      & ~ .glider {
        transform: translateX(0);
      }
    }
  }

  input[id='Stars'] {
    &:checked {
      & ~ .glider {
        transform: translateX(100%);
      }
    }
  }

  .glider {
    z-index: 5;
    position: absolute;
    box-sizing: border-box;
    padding: 16px;
    top: 8;
    left: 8;
    display: flex;
    width: 70px;
    height: 24px;
    background-color: #e8fdf4;
    border-radius: 10px;
    transition: 0.25s ease-out;
  }
`

const RadioButton = () => {
  return (
    <Wrapper>
      <div className="tabs">
        <input type="radio" id="Newest" name="tabs" defaultChecked />
        <label className="tab" htmlFor="Newest">
          등록 순
        </label>
        <input type="radio" id="Stars" name="tabs" />
        <label className="tab" htmlFor="Stars">
          추천 순
        </label>
        <span className="glider"></span>
      </div>
    </Wrapper>
  )
}

export default RadioButton
