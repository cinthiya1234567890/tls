import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './query.reducer';
import { IQuery } from 'app/shared/model/query.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IQueryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const QueryDetail = (props: IQueryDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { queryEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Query [<b>{queryEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="queryName">Query Name</span>
          </dt>
          <dd>{queryEntity.queryName}</dd>
          <dt>
            <span id="peckOrder">Peck Order</span>
          </dt>
          <dd>{queryEntity.peckOrder}</dd>
          <dt>Profile</dt>
          <dd>{queryEntity.profileName ? queryEntity.profileName : ''}</dd>
        </dl>
        <Button tag={Link} to="/query" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/query/${queryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ query }: IRootState) => ({
  queryEntity: query.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(QueryDetail);
