import styled, { css } from 'styled-components'

export const ContentWrap = styled.div`
  width: 80%;
  max-width: 800px;
  min-height: calc(100% - 300px);
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  padding: 50px 60px;
  gap: 20px;
  border-radius: 20px;
  background-color: #eee;
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
  justify-content: flex-start;
  align-items: center;
  gap: 12px;
  flex-grow: 1;
  padding: 8px;
  background-color: #fff;
  border-radius: 8px;
  border-bottom: 1px solid var(--border-01);
  box-shadow: var(--box-shadow-list);
`

export const Category = styled.span`
  display: flex;
  align-items: center;
  padding: 8px 12px;
  border-radius: 8px;
  color: #fff;
  font-size: 0.9rem;
  background-color: var(--ecogreen-01);
`

export const PlaceName = styled.span`
  color: var(--ecogreen-01);
  font-weight: bold;
  font-size: 1.1rem;
`

export const Address = styled.span`
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
