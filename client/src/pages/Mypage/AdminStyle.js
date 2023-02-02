import styled from 'styled-components'

export const ContentWrap = styled.div`
  width: 80%;
  max-width: 800px;
  max-height: 600px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  padding: 50px 60px;
  gap: 20px;
  border-radius: 20px;
  background-color: #eee;
  overflow-y: auto;
`

export const Summary = styled.div`
  display: flex;
  justify-content: right;
  align-items: center;
  gap: 4px;
  width: 100%;
  font-size: 1rem;
  text-align: right;
`

export const Span = styled.span`
  padding: 4px 6px;
  border-radius: 4px;
  color: #fff;
  font-size: 0.9rem;
  text-align: center;
  background-color: ${props => props.bgColor};
`

export const List = styled.ul`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  gap: 8px;
`

export const Item = styled.li`
  width: 100%;
  display: flex;
  justify-content: center;
  gap: 12px;
`

export const FieldName = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 12px;
  flex-grow: 1;
  padding: 8px;
  background-color: #fff;
  border-radius: 8px;
  border-bottom: 1px solid var(--border-01);
  box-shadow: var(--box-shadow-list);
`

export const Head = styled.div`
  width: 110px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 8px;
  border-radius: 8px;
  color: #fff;
  font-size: 0.9rem;
  background-color: var(--ecogreen-01);
`

export const Title = styled.div`
  display: flex;
  align-items: center;
  gap: 4px;
  flex-grow: 1;
  color: var(--ecogreen-01);
  font-weight: bold;
  font-size: 1.1rem;
`

export const Tail = styled.div`
  color: #999999;
  font-size: 0.85rem;
  letter-spacing: -0.6px;
`

export const DelButton = styled.button`
  width: 54px;
  background-color: #fff;
  border-radius: 8px;
  border-bottom: 1px solid var(--border-01);
  box-shadow: var(--box-shadow-list);

  & svg {
    font-size: 1.4rem;
  }

  &:hover {
    filter: brightness(0.85);
  }
`
