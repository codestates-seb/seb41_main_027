import React from 'react'
import styled, { css } from 'styled-components'

const PaginationStyle = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 30px;
  margin-top: 12px;
  gap: 10px;
`

const PageButton = styled.button`
  padding: 2px 8px;
  border: 1px solid var(--ecogreen-01);
  border-radius: 4px;
  box-shadow: var(--box-shadow-item);
  ${props =>
    props.nowPage &&
    css`
      color: #fff;
      background-color: var(--ecogreen-01);
    `}
`

const Pagination = props => {
  const { page, totalPages, movePage } = props

  // page list calculate
  const pageCnt = 5
  const pageBlock = Math.ceil(page / pageCnt)
  const isNextBlcok = totalPages > pageBlock * pageCnt
  const isPrevBlock = page > pageCnt
  const sPage = (pageBlock - 1) * pageCnt + 1
  const ePage = sPage + pageCnt - 1 > totalPages ? totalPages : sPage + pageCnt - 1
  const pageList = new Array(ePage - sPage + 1).fill().map((el, idx) => sPage + idx)

  return (
    <PaginationStyle>
      {isPrevBlock && (
        <PageButton value={sPage - pageCnt} onClick={movePage}>
          &lt;
        </PageButton>
      )}
      {pageList.map(pageNum => (
        <PageButton key={pageNum} nowPage={page === pageNum} value={pageNum} onClick={movePage}>
          {pageNum}
        </PageButton>
      ))}
      {isNextBlcok && (
        <PageButton value={pageBlock * pageCnt + 1} onClick={movePage}>
          &gt;
        </PageButton>
      )}
    </PaginationStyle>
  )
}

export const MemoPagination = React.memo(Pagination)
